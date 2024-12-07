import { Navigate, Route, Routes, useLocation } from "react-router-dom";
import Login from "./pages/auth/Login";
import Signup from "./pages/auth/Signup";
import { isLoggedIn } from "./hooks/useAuth";
import Home from "./pages/main/Home";
import Cart from "./pages/cart/Cart";
import Topbar from "./components/layouts/Topbar";
import NotFound from "./pages/main/NotFound";
import Product from "./pages/product/Product";

const App = () => {
  const loggedIn = isLoggedIn();
  const location = useLocation();

  // hide topbar in login and signup screens
  const hideTopBarRoutes = ["/login", "/signup"];
  const showTopBar = !hideTopBarRoutes.includes(location.pathname);

  return (
    <>
      {showTopBar && <Topbar />}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={loggedIn ? <Navigate to={"/"} /> : <Login />} />
        <Route path="/signup" element={loggedIn ? <Navigate to={"/"} /> : <Signup />} />
        <Route path="/cart" element={loggedIn ? <Cart /> : <Navigate to={"/login"}/>} />
        <Route path="/products/:productId" element={<Product />} />
        <Route path="*" element={<NotFound />}/>
      </Routes>
    </>
  );
}

export default App;