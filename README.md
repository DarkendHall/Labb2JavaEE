## Laboration 2

This is the GitHub repository for examination 2 in the course "Java Enterprise".

Student and teacher need to be created before subject.

### Format for Teacher JSON is:

```json
{
  "id": 123,
  "firstName": "teacherFirstName",
  "lastName": "teacherLastName"
}
```

Field "id" is only required when updating an existing teacher

### Format for Subject JSON is:

```json
{
  "id": 123,
  "name": "subjectName",
  "teacher": {
    "id": 123,
    "firstName": "teacherFirstName",
    "lastName": "teacherLastName"
  },
  "students": [
    {
      "firstName": "somefirstname",
      "lastName": "somelastname",
      "email": "some@email.address",
      "id": 123,
      "phoneNumber": "somephonenumber"
    }
  ]
}

```

Field "id" is not only needed when updating an existing subject and field "phoneNumber" is optional for student.

### ENDPOINTS:

- /labb2/teachers                 (addTeacher(Teacher teacher), POST)\
- /labb2/teachers/{id}            (getTeacher(@PathParam("id") Long id), GET)\
- /labb2/teachers/{id}            (patchTeacher(@PathParam("id") Long id, Teacher teacher), PATCH)\
- /labb2/teachers/{id}            (updateTeacher(@PathParam("id") Long id, Teacher teacher), PUT)\
- /labb2/teachers/{id}            (removeTeacher(@PathParam("id") Long id), DELETE)\
- /labb2/teachers                 (getAllTeachers(), GET)\
- /labb2/teachers/query           (getAllTeachers(@QueryParam("lastName") String lastName), GET)


- /labb2/subjects                 (addSubject(Subject subject), POST)\
- /labb2/subjects/{id}            (getSubject(@PathParam("id") Long id), GET)\
- /labb2/subjects/{id}            (updateSubject(@PathParam("id") Long id, Subject subject), PUT)\
- /labb2/subjects/{id}            (removeSubject(@PathParam("id") Long id), DELETE)\
- /labb2/subjects                 (getAllSubjects(), GET)\
- /labb2/subjects/{id}/students   (getAllStudentsInSubject(@PathParam("id") Long id), GET)

### Import for Insomnia

https://github.com/DarkendHall/Labb2JavaEE/blob/main/Insomnia_requests.json

## From Laboration 1

### Format for Student JSON is:

```json
{
  "id": 123,
  "firstName": "somefirstname",
  "lastName": "somelastname",
  "email": "some@email.address",
  "phoneNumber": "somephonenumber"
}
```

Field "id" is only needed when updating and field "phoneNumber" is optional.

### ENDPOINTS:

- /labb2/students         (addStudent(Student student), POST)\
- /labb2/students/{id}    (getStudent(@PathParam("id") Long id), GET)\
- /labb2/students/{id}    (patchStudent(@PathParam("id") Long id, Student student), PATCH)\
- /labb2/students/{id}    (updateStudent(@PathParam("id") Long id, Student student), PUT)\
- /labb2/students/{id}    (removeStudent(@PathParam("id") Long id, DELETE)\
- /labb2/students         (getAllStudents, GET)\
- /labb2/students/query   (getAllStudents(@QueryParam("lastName") String lastName), GET)

### Import for Insomnia

https://github.com/DarkendHall/Labb1JavaEE/blob/main/Insomnia_requests.json
