#!/usr/bin/perl

my %color_of;

my $fruit = 'apple';
$color_of{$fruit} = 'red';
$color_of{orange} = "orange";
$color_of{grape} = "purple";

print "$color_of{orange}\n";

my @fruits = keys %color_of;
for my $fruit (@fruits) {
    print "The color of '$fruit' is $color_of{$fruit}\n";
}

%HoH = (
    flintstones => {
        husband   => "fred",
        pal       => "barney",
    },
    jetsons => {
        husband   => "george",
        wife      => "jane",
        "his boy" => "elroy",  # Key quotes needed.
    },
    simpsons => {
        husband   => "homer",
        wife      => "marge",
        kid       => "bart",
    },
);

$HoH{ mash } = {
    captain  => "pierce",
    major    => "burns",
    corporal => "radar",
};

for $family ( keys %HoH ) {
    print "$family: ";
    for $role ( keys %{ $HoH{$family} } ) {
         print "$role=$HoH{$family}{$role} ";
    }
    print "\n";
}