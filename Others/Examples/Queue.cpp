#include  <stdio.h>
#include  <stdlib.h>
#include  <string.h>

typedef struct nodeT
{
    struct nodeT *left;
    struct nodeT *right;
    int value;
} node;

struct element
{
    node* tnode;
    struct element* next;
};

struct queue
{
    struct element* head;
    struct element* tail;
};

struct queue* queue_add_element( struct queue*, node*);
struct queue* queue_remove_element( struct queue*);


struct queue* queue_new(void);
struct queue* queue_free( struct queue* );

void queue_print( const struct queue* );
void queue_print_element(const struct element* );
bool check_empty_queue(const struct queue*);
element* queue_front(const struct queue*);

int create_binary_search_tree(node **root);
void pre_oder_traversal(node *root);
void leverl_order_traversl(node *root);

int main(int argc, char * argv[])
{
    node * root;
    node **p_root = &root;
    create_binary_search_tree(p_root);
    //pre_oder_traversal(root);
    leverl_order_traversl(root);
    return 0;
}

/* Will always return the pointer to queue */
struct queue* queue_add_element(struct queue* s,node* t)
{
    struct element* p = (element*)malloc( 1 * sizeof(*p) );

    if( NULL == p )
    {
        fprintf(stderr, "IN %s, %s: malloc() failed\n", __FILE__, "queue_add");
        return s;
    }

    p->tnode = t;
    p->next = NULL;


    if( NULL == s )
    {
        printf("Queue not initialized\n");
        free(p);
        return s;
    }
    else if( NULL == s->head && NULL == s->tail )
    {
	      /* printf("Empty queue, adding p->num: %d\n\n", p->num);  */
        s->head = s->tail = p;
        return s;
    }
    else if( NULL == s->head || NULL == s->tail )
    {
        fprintf(stderr, "There is something seriously wrong with your assignment of head/tail to the queue\n");
        free(p);
        return NULL;
    }
    else
    {
	      /* printf("queue not empty, adding element to tail\n"); */
        s->tail->next = p;
        s->tail = p;
    }

    return s;
	}


/* This is a queue and it is FIFO, so we will always remove the first element */
struct queue* queue_remove_element( struct queue* s )
{
    struct element* h = NULL;
    struct element* p = NULL;

    if( NULL == s )
    {
        printf("queue is empty\n");
        return s;
    }
    else if( NULL == s->head && NULL == s->tail )
    {
        printf("Well, queue is empty\n");
        return s;
    }
    else if( NULL == s->head || NULL == s->tail )
    {
        printf("There is something seriously wrong with your queue\n");
        printf("One of the head/tail is empty while other is not \n");
        return s;
    }

    h = s->head;
    p = h->next;
    free(h);
    s->head = p;
    if( NULL == s->head )
    {
        s->tail = s->head;   /* The element tail was pointing to is free(), so we need an update */
    }

    return s;
}

/* ---------------------- small helper fucntions ---------------------------------- */
struct queue* queue_free( struct queue* s )
{
    while( s->head )
    {
        queue_remove_element(s);
    }

    return s;
}

struct queue* queue_new(void)
{
    struct queue* p = (queue*)malloc( 1 * sizeof(*p));

    if( NULL == p )
    {
        fprintf(stderr, "LINE: %d, malloc() failed\n", __LINE__);
    }

    p->head = p->tail = NULL;

    return p;
}

void queue_print( const struct queue* ps )
{
    struct element* p = NULL;

    if( ps )
    {
        for( p = ps->head; p; p = p->next )
        {
            queue_print_element(p);
        }
    }

    printf("------------------\n");
}

void queue_print_element(const struct element* p )
{
    if( p )
    {
        printf("Num = %d\n", p->tnode->value);
    }
    else
    {
        printf("Can not print NULL struct \n");
    }
}

element* queue_front(const struct queue* q)
{
    return q->head;
}

int create_binary_search_tree(node **root)
{
    // first level node
    *root = (node*)malloc(sizeof(*root));
    if(!root)
    {
        return -1;
    }
    (*root)->value = 100;

    // second level node
    node *child1 = (node*)malloc(sizeof(*child1));
    if(!child1)
    {
        return -1;
    }
    child1->value = 50;
    node *child2 = (node*)malloc(sizeof(*child2));
    if(!child2)
    {
        return -1;
    }
    child2->value = 150;
    (*root)->left = child1;
    (*root)->right = child2;

    // third level node
    // left subtree
    node *child1_1 = (node*)malloc(sizeof(*child1_1));
    if(!child1_1)
    {
        return -1;
    }
    child1_1->value = 25;
    child1_1->left = 0;
    child1_1->right = 0;
    node *child1_2 = (node*)malloc(sizeof(*child1_2));
    if(!child1_2)
    {
        return -1;
    }
    child1_2->value = 75;
    child1_2->left = 0;
    child1_2->right = 0;
    child1->left = child1_1;
    child1->right = child1_2;
    // right subtree
    node *child2_1 = (node*)malloc(sizeof(*child2_1));
    if(!child2_1)
    {
        return -1;
    }
    child2_1->value = 125;
    child2_1->left = 0;
    child2_1->right = 0;
    node *child2_2 = (node*)malloc(sizeof(*child2_2));
    if(!child2_2)
    {
        return -1;
    }
    child2_2->value = 175;
    child2_2->left = 0;
    child2_2->right = 0;
    child2->left = child2_1;
    child2->right = child2_2;

    // forth level node
    node *child3_1 = (node*)malloc(sizeof(*child3_1));
    if(!child3_1)
    {
        return -1;
    }
    child3_1->value = 110;
    child3_1->left = 0;
    child3_1->right = 0;
    child2_1->left = child3_1;
	return 0;
}

void pre_oder_traversal(node *root)
{
    if(!root)
    {
        return;
    }
    printf("%d ",root->value);
    pre_oder_traversal(root->left);
    pre_oder_traversal(root->right);
}

void leverl_order_traversl(node *root)
{
    struct queue*  q = NULL;
    q = queue_new();
    queue_add_element(q,root);
	while(!check_empty_queue(q))
	{
		node *currentNode = queue_front(q)->tnode;
        printf("%d ",currentNode->value);
		queue_remove_element(q);
		if (currentNode->left)
		{
			queue_add_element(q,currentNode->left);
		}
		if(currentNode->right)
		{
			queue_add_element(q,currentNode->right);
		}
	}
    queue_free(q);   /* always remember to free() the malloc()ed memory */
    free(q);        /* free() if queue is kept separate from free()ing the structure, I think its a good design */
    q = NULL;      /* after free() always set that pointer to NULL, C will run havon on you if you try to use a dangling pointer */
}

bool check_empty_queue(const struct queue* s)
{
    if( NULL == s->head && NULL == s->tail )
    {
        return true;
    }

    else if( (NULL == s->head && NULL != s->tail) || (NULL != s->head && NULL == s->tail))
    {
        printf("There is something seriously wrong with your assignment of head/tail to the queue\n");
        return false;
    }
    else
    {
        return false;
    }
}
