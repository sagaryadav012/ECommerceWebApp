# ECommerceWebApp




Category APIs :
Create new category(POST Request) : http://localhost:8080/category/insert
-> When create category, first it checks category exists in db, if exists returns category else 
   it will create new category and returns it.   
-> Json Body : 
   {
        "name" : "categoryName"
   }

Fetch category by name(GET Request) : http://localhost:8080/category/fetch/categoryName
-> It fetches category by name, if category not exists throws exception else returns category.

Fetch all categories(GET Request) : http://localhost:8080/categoryfetchAll
-> It fetches all categories.


Product APIs :
Create new product(POST Request) : http://localhost:8080/product/insert
-> It creates new product. If the category of product not exists in db, it creates category first, 
   then creates new product.
-> Json Body :
    {
        "title" : "ProductTitle",
        "description" : "ProductDescription",
        "price" : "5000.0",
        "availableQuantity" : "10",
        "categoryDto" : {
            "name" : "categoryName"
        }
    }

Fetch Product(GET Request) : http://localhost:8080/product/fetch/id
-> It fetches product from db. If not exists throws exception.

Fetch all products(GET Request) : http://localhost:8080/product/fetchAll
-> It fetches all products from db.

Update price of product(PATCH Request) : http://localhost:8080/product/updatePrice
-> It updates price of product.
-> Json body :
    {
        "id" : "1", // product id
        "price" : "10000.00"
    }

Update available quantity of product(PATCH Request) : http://localhost:8080/product/updateQuantity
-> It updates available quantity of product.
-> Json body :
    {
        "id" : "3", // product id
        "quantity" : "5"
    }

Delete product(DELETE Request) : http://localhost:8080/product/2
-> It deletes product in db.





USER APIs :

Register user(POST Request) : http://localhost:8080/user/register
-> It registers user, if given mail already not exists in db then it creates new user.
-> It stores password as encoded password. Used BCryptPasswordEncoder for encode and decode.
-> Json Body :
    {
        "name" : "username",
        "mail" : "mail id",
        "password" : "password"
    }

Get user(GET Request) : http://localhost:8080/user/1
-> Fetch user from db.

Login user(POST Request) : http://localhost:8080/user/login
-> It checks login details, If correct then returns a token.
-> Json Body :
    {
        "mail" : "user mail",
        "password" : "password"
    }

Logout user(DELETE Request) : http://localhost:8080/user/logout
-> It takes token value, and set it to expire.
-> Json Body :
    {
        "value" : "TokenValue"
    }



CART APIs : 
-> All Cart APIs need valid tokens.
-> Send token value(not expired) in header for all cart apis
Example :
    Key : value, Value : "tokenValue"

View Cart(GET Request) : http://localhost:8080/cart/1
-> It fetches cart.

Add product to cart(POST Request) : http://localhost:8080/cart/addCartItem
-> It adds product to cart. If product exists in cart then it suggests to increment quantity.
-> Once you add product to cart, then the total amount of cart value will be changed.
-> Json Body :
    {
        "cart_id" : "1",
        "product_id" : "3",
        "quantity" : "2"
    }

Remove product from cart(DELETE Request) : http://localhost:8080/cart/removeCartItem
-> It deletes products from cart, If not exists throws exception.
-> Once it deleted then it update total amount value of cart.
-> Json Body :
    {
        "cart_id" : "1",
        "product_id" : "3"
    }

Update quantity of product in cart(PATCH Request) : http://localhost:8080/cart/updateQuantity
-> It updates quantity of product in cart, If product not exists throws exception.
-> Json Body : 
    {
    "cart_id" : "1",
    "product_id" : "2",
    "quantity" : "5"
    }



ORDER APIs :
-> All Order APIs need valid tokens.
-> Send token value(not expired) in header for all order apis
Example :
Key : value, Value : "tokenValue"

Fetch Order(GET Request) : http://localhost:8080/order/1
-> It fetched order from db.

Fetch All Orders(GET Request) : http://localhost:8080/order
-> It fetches all orders from db.

Create Order(POST Request) : http://localhost:8080/order/createOrder
-> It takes user id and makes order.
-> Json Body :
    {
        "user_id" : "1"
    }