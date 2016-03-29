#!/usr/bin/perl

my $str = "root:*:0:0:System Administrator:/var/root:/bin/sh";
my ($username, $password, $uid, $gid, $real_name, $home, $shell) = split /:/, $str;
print "$username\n";
print "$real_name\n";

my @fields = split /:/, $str;
my ($username, $real_name) = @fields[0, 4];
print "$username\n";
print "$real_name\n";

use Data::Dumper qw(Dumper);
 
my $str = "fname=Foo&lname=Bar&email=foo@bar.com";
my @words = split /[=&]/, $str;

print Dumper \@words;


my $str = "fname\t\tFoo\tlname\tBar\t\temail=foo@bar.com";
my @words = split /\t/, $str;
my $word_size = @words;

print "word_size = $word_size \n";
print Dumper \@words;