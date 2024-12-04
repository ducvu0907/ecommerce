import { AuthContext } from "@/contexts/AuthContext";
import { useToast } from "@/hooks/use-toast";
import { ApiResponse, Auth, AuthRequest } from "@/types/models";
import { useMutation } from "@tanstack/react-query";
import { useContext } from "react";
import { _request } from "./request";

const authenticateRequest = async (request: AuthRequest): Promise<ApiResponse<Auth>> => {
  return _request<ApiResponse<Auth>>({
    url: "api/auth/token",
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: request
  });
};

export const authenticate = () => {
  const { setRole, setUserId } = useContext(AuthContext);
  const { toast } = useToast();

  return useMutation({
    mutationFn: authenticateRequest,
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
    onSuccess(data: ApiResponse<Auth>) {
      if (data.result) {
        console.log("User authenticated: ", data)
        setRole(data.result.role);
        setUserId(data.result.userId);
      } else {
        throw new Error(data.message);
      }
    },
  });
};