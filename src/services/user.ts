import { User } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";

const getUserRequest = async (userId: string): Promise<ApiResponse<User>> => {
  return _request<ApiResponse<User>>({
    url: `api/user/${userId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"},
  });
};

export const getUser = (userId: string) => {
  return useQuery<ApiResponse<User>, Error>({
    queryKey: ["users", userId],
    queryFn: () => getUserRequest(userId)
  });
};
