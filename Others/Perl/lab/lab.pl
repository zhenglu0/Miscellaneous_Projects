#!/usr/bin/perl
# $Header: //depot/common/zluo/training/perl/lab.pl#1 $
# $Author: zluo $
# $Date: 2015/03/14 $
# 
# Basic Perl lecture lab solution.
# This is only one possible correct solution.  As always, remember:
# There's More Than One Way To Do It!

use strict;
use warnings;

# Check for correct number of arguments.
die "Usage: $0 DATA\n" unless @ARGV == 1;

my ($file) = @ARGV;
open my $fh, '<', $file or die "$0: $file: $!\n";

# Our collected data variables have to be declared outside the loop, as
# otherwise they will go out of scope after it ends.
my %headers;
my %data;

while( my $line = <$fh> )
{
    chomp $line; # Remove the newline.
    my @parts = split ' ', $line;
    
    unless( %headers )
    {
        # This is the header line.  The %headers hash maps array indices to
        # column names.
        $headers{$_} = $parts[$_] for 0 .. $#parts;
        
        # Skip to the next line to start processing.
        next;
    }
    
    my %row = map { $headers{$_} => $parts[$_] } 0 .. $#parts;
    
    ############################################################################
    # Process data here
    ############################################################################
    
    # Remember, each word can belong to more than one class, so mapping each row
    # of data to a simple hash table entry won't work.  Instead, I'm using a
    # two-level hash, with the "inner" level holding separate data for each
    # class.
    my ($word, $class) = @row{'word', 'class'};
    $data{$word}{$class} = {
        map { $_ => $row{$_} } qw/rank count/
    };
}

# Close your filehandles once you're done with them.  Also, always check return
# values from system calls!
close $fh or warn "$0: $file: $!\n";


# Each of the following steps would normally be included in a single pass over
# the parsed data, or even in the file-reading loop, for efficiency and brevity.
# I'm writing them each here separately for the sake of demonstration.


################################################################################
# 1. Determine the number of distinct words.
#    
# Simple in this implementation; it's just the number of hash keys.

my $unique = scalar keys %data;
print "There were $unique unique words in the data.\n";

print "\n";


################################################################################
# 2. Find the number of words belonging to each word class.

my %classcount;
for my $classes ( values %data )
{
    $classcount{$_}++ for keys %$classes;
}

print "Number of words belonging to each class:\n";
print "    $_: $classcount{$_}\n" for sort keys %classcount;

print "\n";


################################################################################
# 3. Count the number of words that start with each letter.
# 
# Just pull off the first char with substr(), remembering to normalize case.

my %letters;
for my $word ( keys %data )
{
    my $letter = lc substr $word, 0, 1;
    $letters{$letter}++;
}

print "Number of words starting with each letter:\n";
printf "    $_: %d\n", ($letters{$_} || 0) for 'a' .. 'z';

print "\n";


################################################################################
# 4. Find the most and least common word.
# 
# The simplest solution is to just sort the set of words by frequency, but that
# requires N log N time.  In this case, it's also not immediately clear how to
# sort the data since each word may belong to multiple classes and thus have
# more than one rank.  At any rate we can do it by hand in linear time.

my (%first, %last);
while( my ($word, $classes) = each %data )
{
    while( my ($class, $stats) = each %$classes )
    {
        my $rank = $stats->{rank};
        %first = (word => $word, class => $class, %$stats)
            if not defined $first{rank} or $rank < $first{rank};
        
        %last = (word => $word, class => $class, %$stats)
            if not defined $last{rank} or $rank > $last{rank};
    }
}

sub commonality
{
    my ($desc, %info) = @_;
    print "The $desc common word was '$info{word}' ($info{class}, rank $info{rank}), occurring $info{count} times.\n";
}

commonality( most  => %first );
commonality( least => %last );

print "\n";


################################################################################
# 5. Find the longest and shortest words.
# 
# Essentially the same as (1), but with the additional wrinkle that several
# words may be tied for length.

my (%longest, %shortest);
while( my ($word, $classes) = each %data )
{
    my $len = length $word;
    
    %longest = (length => $len, words => [])
        if not defined $longest{length} or $len > $longest{length};
    push @{ $longest{words} }, $word if $len == $longest{length};
    
    %shortest = (length => $len, words => [])
        if not defined $shortest{length} or $len < $shortest{length};
    push @{ $shortest{words} }, $word if $len == $shortest{length};
}

sub lengthrep
{
    my ($desc, %info) = @_;
    my $words = $info{words};
    my $plural = (@$words > 1);
    
    printf( "The $desc word%s, with length $info{length}, %s: %s\n",
            ($plural ? 's' : ''),
            ($plural ? 'were' : 'was'),
            join( ', ', map { "'$_'" } @$words ) );
}

lengthrep( longest  => %longest );
lengthrep( shortest => %shortest );

print "\n";


################################################################################
# 6. Find the words belonging to the greatest number of distinct classes, and
#    list them along with their classes.

my $max = 0;
my %maxwords;

while( my ($word, $classes) = each %data )
{
    my $num = scalar keys %$classes;
    if( $max < $num )
    {
        $max = $num;
        %maxwords = ();
    }
    
    $maxwords{$word} = $classes
        if $max == $num;
}

print "The following words belonged to $max classes:\n";
for my $word ( sort keys %maxwords )
{
    my $classes = $maxwords{$word};
    print "    '$word':\n";
    print "        $_ (rank $classes->{$_}{rank})\n"
        for sort { $classes->{$a}{rank} <=> $classes->{$b}{rank} } # sorting by rank
            keys %$classes;
}