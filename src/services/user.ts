import { AuthRequest, UserData, UserUpdateRequest } from "@/types/models";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getMyProfileRequest = async (request: AuthRequest): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/me`,
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

const updateMyProfileRequest = async (request: UserUpdateRequest): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/me/update`,
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

const getUserProfileRequest = async (userId: string): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/${userId}`,
    method: "GET",
    headers: { "Content-Type": "application/json" },
  });
};

export const getMyProfile = (token: string) => {
  return useQuery<ApiResponse<UserData>, Error>({
    queryKey: ["users", "me"],
    queryFn: () => getMyProfileRequest({ token })
  });
};

export const updateMyProfile = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (request: UserUpdateRequest) => updateMyProfileRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<UserData>) => {
      if (data.result) {
        console.log("User updated: ", data.result);
        toast({
          title: "Profile updated successfully"
        });
        queryClient.invalidateQueries({queryKey: ["me"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const getUserProfile = (userId: string) => {  
  return useQuery<ApiResponse<UserData>, Error>({
    queryKey: ["users", userId],
    queryFn: () => getUserProfileRequest(userId)
  });
}