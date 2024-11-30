import { useMutation } from "@tanstack/react-query";
import { ApiResponse, LoginRequest, SignupRequest, Token, User } from "@/types/models";
import { useToast } from "./use-toast";
import { useNavigate } from "react-router-dom";
import { useContext, useState } from "react";
import { AuthContext } from "@/contexts/AuthContext";

const isLoggedIn = () => {
  const { token } = useContext(AuthContext);
  if (!token) {
    return false;
  }
  return true;
};

const useAuth = () => {
  const { setToken } = useContext(AuthContext);
  const { toast } = useToast();
  const navigate = useNavigate();

  const loginRequest = async (formData: LoginRequest): Promise<ApiResponse<Token>> => {
    const response = await fetch("/api/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    });

    if (!response.ok) {
      throw new Error("Unexpected error while logging in");
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
      throw new Error("Unexpected error while signing up");
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
      if (data && data.result) {
        console.log("Login successfully", data);
        const token = data.result.token;
        localStorage.setItem("token", token);
        setToken(token);
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
      if (data && data.result) {
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
    navigate("/login");
  };

  return { loginMutation, signupMutation, logout };
};


export { isLoggedIn };
export default useAuth;