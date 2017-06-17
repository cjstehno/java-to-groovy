

- load csv files

id,first,last,dob,gender
100,Chris,Stehno,1/28/73,M

- populate Person objects

class Person {

    Long id
    Name name
    Date dateOfBirth
    Gender gender
}

enum Gender {
    Male('M'), Female('F'), Other('O')
}

- sort the people by last name
- write json files grouped by last name (one per last name)

[
    {
        id:,
        name: { first:, last: },
        dateOfBirth: ,
        gender:
    },
    ...
]

==== show
- gradle
- simple enums
- Pogo (tostring, equalsHash)
- sortable
- loops
- logging
- immutable (name)
- junit to spock testing
- remove need for external libs