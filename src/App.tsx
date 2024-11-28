import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./pages/auth/Login";
import Signup from "./pages/auth/Signup";
import { isLoggedIn } from "./hooks/useAuth";
import Home from "./pages/main/Home";

const App = () => {
  const loggedIn = isLoggedIn();

  return (
    <Routes>
      <Route path="/" element={loggedIn ? <Home /> : <Navigate to={"/login"}/>}/>
      <Route path="/login" element={loggedIn ? <Navigate to={"/"} /> : <Login />}/>
      <Route path="/signup" element={loggedIn ? <Navigate to={"/"} /> : <Signup />} />
    </Routes>
  );
}

export default App;