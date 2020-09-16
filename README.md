# Brackets-CLI
CLI for add brackets to string

Usage: Brackets options_list
Options: 
    --middle, -m [false] -> Insert brackets in the middle of the string in case of even length 
    --edges, -e [false] -> Insert brackets around the edges of the string 
    --brackets, -fb -> List of brackets (always required) { Value should be one of [(), [], {}] }
    --help, -h -> Usage info 


Example:
Program arguments:
`-m -e -fb ();[];{}`

Input: 
`Hello!`

Output:
`(H[e{l()l}o]!)`

