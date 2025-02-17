import { ApiResponse, AuthData } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";

const authenticateRequest = async (): Promise<ApiResponse<AuthData>> => {
  return _request<ApiResponse<AuthData>>({
    url: "/api/auth/validate",
    method: "GET",
  });
};

export const authenticateQuery = () => {
  const token = localStorage.getItem("token");

  return useQuery({
    queryKey: ["token"],
    queryFn: authenticateRequest,
    enabled: !!token
  });
};