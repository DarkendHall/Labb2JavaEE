## Laboration 1

This is the GitHub repository for Laboration 2 in the course "Java Enterprise".



















##From Laboration 1
### Format for Student JSON is:

{ \
"firstName": "somefirstname", \
"lastName": "somelastname", \
"email": "some@email.address", \
"id": 123, (only needed when updating with PUT), \
"phoneNumber": "somephonenumber" (field is a String, and optional) \
}

### ENDPOINTS:

/labb2/students         (addStudent(Student student), POST)

/labb2/students/{id}    (getStudent(@PathParam("id") Long id), GET)

/labb2/students/{id}    (patchStudent(@PathParam("id") Long id, Student student), PATCH)

/labb2/students/{id}    (updateStudent(@PathParam("id") Long id, Student student), PUT)

/labb2/students/{id}    (removeStudent(@PathParam("id") Long id, DELETE)

/labb2/students         (getAllStudents, GET)

/labb2/students/query   (getAllStudents(@QueryParam("lastName") String lastName), GET)

### Import for Insomnia

https://github.com/DarkendHall/Labb1JavaEE/blob/main/Insomnia_requests.json
