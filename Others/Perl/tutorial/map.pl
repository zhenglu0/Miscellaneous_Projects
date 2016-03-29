#!/usr/bin/perl

use Data::Dumper qw(Dumper);
 
my @names = qw(Foo Bar Baz);
my %is_invited = map {$_ => 1} @names;
 
my $visitor = <STDIN>;
chomp $visitor;
 
if ($is_invited{$visitor}) {
   print "The visitor $visitor was invited\n";
}
else {
   print "The visitor $visitor was NOT invited\n";
}
 
print Dumper \%is_invited;

my @p_invited = map {$_ => 1} @names;
print "p_invited @p_invited\n";

my @t_invited = map { $_ =~ /^F/ ? ($_ => 1) : () } @names;
print "t_invited @t_invited\n";
