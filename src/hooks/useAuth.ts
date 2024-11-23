import { useMutation } from "@tanstack/react-query";
import { ApiResponse, LoginRequest, SignupRequest, Token, User } from "@/types/models";
import { useToast } from "./use-toast";
import { useNavigate } from "react-router-dom";
import { useAuth } from "@/contexts/AuthContext";

const isLoggedIn = () => {
  return localStorage.getItem("token") !== null;
};

const loginRequest = async (credentials: LoginRequest): Promise<ApiResponse<Token>> => {
  const response = await fetch("/api/auth/login", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(credentials)
  });

  if (!response.ok) {
    throw new Error("Login failed");
  }

  return response.json();
};

const signupRequest = async (credentials: SignupRequest): Promise<ApiResponse<User>> => {
  const response = await fetch("/api/auth/signup", {
    method: "POST",
    headers: {"Content-Type": "application/json"},
    body: JSON.stringify(credentials)
  });

  if (!response.ok) {
    throw new Error("Signup failed");
  }

  return response.json();
};

const useLogin = () => {
  const {toast} = useToast();
  const { setToken } = useAuth();

  return useMutation({
    mutationFn: loginRequest,
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
    onSuccess: (data: ApiResponse<Token>) => {
      if (data && data.result) {
        console.log(data);
        const token = data.result.token;
        localStorage.setItem("token", token);
        setToken(token);
      } else {
        throw new Error(data.message);
      }
    },
  });
};

const useSignup = () => {
  const {toast} = useToast();
  const navigate = useNavigate();

  return useMutation({
    mutationFn: signupRequest,
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
    onSuccess: (data: ApiResponse<User>) => {
      if (data && data.result) {
        console.log(data);
        navigate("/login");
      } else {
        throw new Error(data.message);
      }
    },
  });
};

const useLogout = () => {
  const { setToken } = useAuth();
  localStorage.removeItem("token");
  setToken(null);
};

export {
  isLoggedIn,
  useLogin,
  useSignup,
  useLogout,
};