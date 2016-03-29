#!/usr/bin/perl

use strict;
use warnings;
 
use Data::Dumper qw(Dumper);
  
my @words = qw(foo bar zorg moo);
print Dumper \@words;
    
my @sorted_words = sort @words;
print Dumper \@sorted_words;
@sorted_words = sort { lc($b) cmp lc($a) } @words;     
print Dumper \@sorted_words;

my @numbers = (14, 3, 12, 2, 23);
my @sorted_numbers = sort @numbers;
print Dumper \@sorted_numbers;
@sorted_numbers = sort { $a <=> $b } @numbers;
print Dumper \@sorted_numbers;
