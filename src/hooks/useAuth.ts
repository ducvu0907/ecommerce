import { useMutation } from "@tanstack/react-query";
import { ApiResponse, LoginRequest, SignupRequest, Token, User } from "@/types/models";
import { useToast } from "./use-toast";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "@/contexts/AuthContext";
import { authenticate } from "@/services/auth";

const isLoggedIn = () => {
  const { token } = useContext(AuthContext);
  if (!token) {
    return false;
  }
  return true;
};

const isSeller = () => {
  const { role } = useContext(AuthContext);
  return role === "seller";
};

const useAuth = () => {
  const { setToken, setRole, setUserId } = useContext(AuthContext);
  const { toast } = useToast();
  const authenticateMutate = authenticate();
  const navigate = useNavigate();

  const loginRequest = async (formData: LoginRequest): Promise<ApiResponse<Token>> => {
    const response = await fetch("/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    });

    if (!response.ok) {
      throw new Error("Network request failed: unable to log in");
    }

    return response.json();
  };

  const signupRequest = async (formData: SignupRequest): Promise<ApiResponse<User>> => {
    const response = await fetch("/api/auth/signup", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    });

    if (!response.ok) {
      throw new Error("Network request failed: unable to sign up");
    }

    return response.json();
  };

  const loginMutation = useMutation({
    mutationFn: loginRequest,
    onError: (error: Error) => {
      toast({
        title: error.message,
      });
    },
    onSuccess: (data: ApiResponse<Token>) => {
      if (data.result) {
        console.log("Login successfully", data);
        const token = data.result.token;
        localStorage.setItem("token", token);
        setToken(token);
        authenticateMutate.mutate({ token });
      } else {
        throw new Error(data.message);
      }
    },
  });

  const signupMutation = useMutation({
    mutationFn: signupRequest,
    onError: (error: Error) => {
      toast({
        title: error.message,
      });
    },
    onSuccess: (data: ApiResponse<User>) => {
      if (data.result) {
        console.log("Signup successfully", data);
        navigate("/login");
      } else {
        throw new Error(data.message);
      }
    },
  });

  const logout = () => {
    localStorage.removeItem("token");
    setToken(null);
    setRole(null);
    setUserId(null);
    navigate("/login");
  };

  return { loginMutation, signupMutation, logout };
};


export { isLoggedIn, isSeller };
export default useAuth;