(async () => {
   let localUser = JSON.parse(window.localStorage.getItem("localUser"))
   VerifyTokenValidity()// iportar
   localUser = JSON.parse(window.localStorage.getItem("localUser"))
   const users = await getUsers(localUser)
   fillUsers(users)
})();



async function getUsers(localUser) {
   const URL = `http://localhost:8080/users`
  
   const promise = await fetch(URL, {
      method: 'get',
      headers: {
         'Authorization': `Bearer ${localUser.tokens.access_token}`
      }
   })

   const users = await promise.json()
  return users
}

function fillUsers(users) {
   let booksElement = ''

   users.map(u => {
      booksElement += getUserDiv(u)
   })
   document.getElementById('books').innerHTML += booksElement
}

const getUserDiv = (user) =>
`
 <tr>
 <td >${user.id}</td>
 <td >${user.name}</td>
 <td >${user.numberOfBooks}</td>
 <td><a href="#" class="delete"  id=${user.id} onClick="deleteUser()" >Eliminar </a></td>
 </tr>
 `
 


function VerifyTokenValidity() {
   const localUser = JSON.parse(window.localStorage.getItem("localUser"))

   console.log(localUser);

   const URL = `http://localhost:8080/oauth/check_token?token=${localUser.tokens.access_token}`

   fetch(URL, {
      method: 'post',
      headers: {
         'Authorization': 'Basic ' + btoa('client:123')
      }
   }).then(res => {

      if (res.status == 401 || res.status == 400) {
         RefreshAccessToken()
      }

    })


}










async function RefreshAccessToken() {

   let localUser = JSON.parse(window.localStorage.getItem("localUser"))
   console.log(localUser.tokens.refresh_token);

   const URL = `http://localhost:8080/oauth/token?grant_type=refresh_token&refresh_token=${localUser.tokens.refresh_token}`
   fetch(URL, {
      method: 'post',
      headers: {
         'Authorization': 'Basic ' + btoa('client:123')
      }
   })
      .then(res => {

         if (res.status == 200) {

            res.json().then(body => {
               //update the access token
               console.log(body);
               localUser.tokens.access_token = body.access_token
               window.localStorage.setItem("localUser", JSON.stringify(localUser))

               window.location.reload()
            })

         } else window.location.href = "login.html"

      })
}

function GoBack(){
   history.back()
}
