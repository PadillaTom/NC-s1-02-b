# s1-02-t
Medical consultation by video calls.

<h2><a href="http://localhost:8080/swagger-ui/index.html">Link Swagger</a> </h2>



USERS SEED
<table>
<thead>
<tr>
<th>email</th>
<th>role</th>
</tr>
</thead>
<tbody>
<tr>
<td>admin{id}@test.com</td>
<td>admin</td>
</tr>
<tr>
<td>user{id}@test.com</td>
<td>user</td>
</tr>
  <tr>
<td>professional{id}@test.com</td>
<td>professional</td>
</tr>
</tbody>
</table>



By default, 3 users with admin role and 3 users with user role will be created where the {id} in the email is a number from 1 to 4 per role. All the users have "12345678" as password.
ROLES SEED
<table>
<thead>
<tr>
<th>ID</th>
<th>Description</th>
<th>Name</th>
</tr>
</thead>
<tbody>
<tr>
<td>1</td>
<td>ROLE_USER</td>
<td>USER</td>
</tr>
<tr>
<td>2</td>
<td>ROLE_ADMIN</td>
<td>ADMIN</td>
</tr>
 <tr>
<td>3</td>
<td>ROLE_PROFESSIONAL</td>
<td>PROFESSIONAL</td>
</tr>
</tbody>
</table>
