(async () => {
   let localUser = JSON.parse(window.localStorage.getItem("localUser"))
   VerifyTokenValidity()
   localUser = JSON.parse(window.localStorage.getItem("localUser"))
   const books = await getBooks(localUser)
   fillBooks(books)
})();




async function getBooks(localUser) {
   const URL = `http://localhost:8080/user/books`
   const promise = await fetch(URL, {
      method: 'get',
      headers: {
         'Authorization': `Bearer ${localUser.tokens.access_token}`
      }
   })

   const books = await promise.json()
  // console.log("iside" + JSON.stringify(books))
   return books
}

function fillBooks(books) {
   let booksElement = ''

   books.map(e => {
      booksElement += getBookDiv(e)
   })
   document.getElementById('books').innerHTML += booksElement
}

const getBookDiv = (e) =>
   `
 <tr>
 <td >${e.name}</td>
 <td >${e.rating}</td>
 <td><a href="#" class="delete"  id=${e.id} onClick="deleteBook()" >Eliminar </a></td>
 </tr>
 `

function deleteBook() {
   VerifyTokenValidity()
   const localUser = JSON.parse(window.localStorage.getItem("localUser"))
   const id = event.target.id
   const URL = `http://localhost:8080/user/books/${id}`

   console.log(URL)
   fetch(URL, {
      method: 'delete',
      headers: {
         'Authorization': `Bearer ${localUser.tokens.access_token}`
      }
   }).then(e=>window.location.reload())

}

 function addBook() {
   const name=document.getElementById("name").value
   const rating =document.getElementById("rating").value
   let msg=''

   if(rating=='classifique o livro') msg+="Escolha uma nota para o livro"


   else if(name.length<2) msg+='Disponibilize o nome do livro'


   if(msg.length>0){   
      document.getElementById("errorAddBook").removeAttribute("hidden","")
      document.getElementById("errorAddBook").innerHTML+=`<p>${msg}</p>`  
   }
   else{
       document.getElementById("errorAddBook").setAttribute("hidden","")
       console.log(name+'  '+rating)//enviar bolo
    }
 }

function postBook(book) {
   //fetch
}









function VerifyTokenValidity() {
   const localUser = JSON.parse(window.localStorage.getItem("localUser"))

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

         } else window.location.href = "/login.html"

      })
}