import { useMutation } from "@tanstack/react-query";
import { ApiResponse, AuthRequest, LoginRequest, SignupRequest, TokenData, UserData } from "@/types/models";
import { useToast } from "./use-toast";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "@/contexts/AuthContext";
import { authenticate } from "@/services/auth";
import { _request } from "@/services/request";

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

  const loginRequest = async (formData: LoginRequest): Promise<ApiResponse<TokenData>> => {
    return _request<ApiResponse<TokenData>>({
      url: "api/auth/login",
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    });
  };

  const signupRequest = async (formData: SignupRequest): Promise<ApiResponse<UserData>> => {
    return _request<ApiResponse<UserData>>({
      url: "api/auth/signup",
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(formData)
    });
  };

  const loginMutation = useMutation({
    mutationFn: loginRequest,
    onError: (error: Error) => {
      toast({
        title: error.message,
      });
    },
    onSuccess: (data: ApiResponse<TokenData>) => {
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
    onSuccess: (data: ApiResponse<UserData>) => {
      if (data.result) {
        console.log("Signup successfully", data);
        toast({
          title: "Signup successfully"
        });
        navigate("/login");
      } else {
        throw new Error(data.message);
      }
    },
  });

  const logout = () => {
    const request: AuthRequest = {token: localStorage.getItem("token") || ""};
    // fetch without awaiting
    fetch("api/auth/logout", {
      method: "POST",
      headers: {"Content-Type":"application/json"},
      body: JSON.stringify(request)
    });
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