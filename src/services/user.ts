import { UserData } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";

const getUserRequest = async (userId: string): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/${userId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"},
  });
};

export const getUser = (userId: string) => {
  return useQuery<ApiResponse<UserData>, Error>({
    queryKey: ["users", userId],
    queryFn: () => getUserRequest(userId)
  });
};
