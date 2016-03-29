#include <stdio.h>
#include <stdlib.h>

//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
/* Linked List methods
 These methods are a partial implementation of a singly
 linked list with head and tail pointers.
 
 The methods use a struct to represent a singly linked
 list node.
 
 The linked list operations are implemented such that they
 take in head and tail pointers of the list and modify
 them.  In order to modify the head and tail pointers we
 pass in, we instead pass /pointers/ to the head and tail
 pointers.
 */

typedef struct llnode
{
    int val;
    struct llnode * next;
} node;

/*
 Adds an element to the head of the linked list with head
 *head and tail *tail.
 */
void llAddToHead(node **head, node **tail, int val)
{
    node * tmp;
    tmp = (node*) malloc (sizeof(node));
    tmp->val = val;
	tmp->next = NULL;
    if (*head != NULL) {
        tmp->next = *head;
        *head = tmp;
    } else {
        *head = tmp;
        *tail = tmp;
    }
}

/*
 Move a node to the head of the linked list with head
 *head and tail *tail.
 */
void llMoveToHead(node **head, node **tail, node *node)
{
	node->next = NULL;
    if (*head != NULL) {
        node->next = *head;
        *head = node;
    } else {
        *head = node;
        *tail = node;
    }
}

/*
 Adds an element to the tail of the linked list with head
 *head and tail *tail.
 */
void llAddToTail(node **head, node **tail, int val)
{
    node * tmp;
    tmp = (node*) malloc (sizeof(node));
    tmp->val = val;
	tmp->next = NULL;
    if (*tail != NULL) {
        (*tail)->next = tmp;
        *tail = tmp;
    } else {
        *head = tmp;
        *tail = tmp;
    }
}

/*
 Prints out the values in the linked list with head head.
 */
void llPrint(node * head)
{
    node * pos = head;
    while (pos != NULL) {
        printf("%d->",pos->val);
        pos = pos->next;
    }
    printf("NULL\n");
}

/*
 Compares two linked lists, beginning at head0 and head1
 respectively.  Returns 1 if their elements are equal and
 0 otherwise.
 */
int llEqual(node* head0, node* head1)
{
    while (head0 != NULL && head1 != NULL) {
        if (head0->val != head1->val)
            return 0;
        head0 = head0->next;
        head1 = head1->next;
    }
    if (head0 == NULL && head1 == NULL)
        return 1;
    else
        return 0;
}

/*
 Creates a copy of the list beginning at srcHead.  The
 resulting copy has head *dstHead and tail *dstTail.
 */
void llCopy(node *srcHead, node **dstHead, node **dstTail)
{
    if (dstHead != NULL && dstTail != NULL) {
        while (srcHead != NULL) {
            llAddToTail(dstHead, dstTail, srcHead->val);
            srcHead = srcHead->next;
        }
    }
}

/*
 step1. Take the top card off the deck and set it on the table
*/
void step1(node **bHeadPtr, node **bTailPtr, node **aHeadCopyPtr)
{
    node *tmpHeadNext = (*aHeadCopyPtr)->next;
    llMoveToHead (bHeadPtr, bTailPtr, *aHeadCopyPtr);
    *aHeadCopyPtr = tmpHeadNext;
}

/*
 step2. Take the next card off the top and put it on the bottom of the deck in your hand.
*/
void step2(node **aHeadCopyPtr, node **aTailCopyPtr)
{
	if (*aHeadCopyPtr == *aTailCopyPtr)
		return;
    node *tmpHeadNext = (*aHeadCopyPtr)->next;
    (*aHeadCopyPtr)->next = NULL;
    (*aTailCopyPtr)->next = *aHeadCopyPtr;
    *aTailCopyPtr = *aHeadCopyPtr;
    *aHeadCopyPtr = tmpHeadNext;
}

//=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~
// Card shuffling algorithm.

/*
 You are given a deck containing n cards.  While holding the deck:
 1. Take the top card off the deck and set it on the table
 2. Take the next card off the top and put it on the bottom of the deck in your hand.
 3. Continue steps 1 and 2 until all cards are on the table.  This is a round.
 4. Pick up the deck from the table and repeat steps 1-3 until the deck is in the original order.
 This program returns the number of rounds necessary to get back to the original deck. We start with a deck (which we call the primary deck) with head aHead and tail aTail.  We make a copy of the primary deck so that we can manipulate it and compare to the original deck.  We carry out steps 1-2 and represent the cards on the table as the /secondary deck/.  Then we set the primary deck copy to the secondary deck and repeat until the secondary deck equals the original primary deck.
 */
int card(node * aHead, node * aTail)
{
    // Make a copy of primary deck.
    node * aHeadCopy = NULL;
    node * aTailCopy = NULL;
    node ** aHeadCopyPtr = &aHeadCopy;
    node ** aTailCopyPtr = &aTailCopy;
    llCopy(aHead, aHeadCopyPtr, aTailCopyPtr);
    // Initialize secondary deck head and tail pointers
    node * bHead = NULL;
    node * bTail = NULL;
    node ** bHeadPtr = &bHead;
    node ** bTailPtr = &bTail;
    // Initialize count
    int count = 0;
    while (!llEqual(aHead, *bHeadPtr)) {
        count++;
        // Re-initialize head and tail pointers for secondary deck.
        bHead = NULL;
        bTail = NULL;
        bHeadPtr = &bHead;
        bTailPtr = &bTail;
        // Do the step 1 and 2
        while (aHeadCopy != NULL) {
                step1(bHeadPtr, bTailPtr, aHeadCopyPtr);
            if (*aHeadCopyPtr != NULL) {
                step2(aHeadCopyPtr, aTailCopyPtr);
            }
        }
        // Move secondary deck to primary deck
        aHeadCopy = bHead;
        aTailCopy = bTail;
	// You may need to print the information
	//llPrint(aHeadCopy);
    }
    return count;
}

int main (int argc, char *argv[])
{
    // Initialize head and tail pointers, and pointers to those pointers.
    node *head, *tail;
    node **headPtr, **tailPtr;
    head = NULL;
    tail = NULL;
    headPtr = &head;
    tailPtr = &tail;
    // Add values to list.
    int i = 0;
    int numCards = 0;
    // Check the arguments passed into main
    if (argc == 2)
        numCards = atoi(argv[1]);
    else
        printf("There should be one argument pass to the main function!!! \n");
    // Initialize the Deck
    while (i < numCards) {
        llAddToTail(headPtr,tailPtr,i);
        i++;
    }
    // Do card shuffling algorithm.
    if (argc == 2)
        printf("Total times = %d\n",card(head,tail));
    
    return 0;
}

