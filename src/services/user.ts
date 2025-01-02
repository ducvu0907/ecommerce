import { UpdateMeRequest, UserData } from "@/types/models";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getMyProfileRequest = async (): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/me`,
    method: "GET",
  });
};

const getUserProfileRequest = async (userId: string): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/${userId}`,
    method: "GET",
  });
};

const updateMyProfileRequest = async (request: UpdateMeRequest): Promise<ApiResponse<UserData>> => {
  return _request<ApiResponse<UserData>>({
    url: `/api/users/me`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

export const getMyProfileQuery = () => {
  return useQuery<ApiResponse<UserData>, Error>({
    queryKey: ["users", "me"],
    queryFn: getMyProfileRequest
  });
};

export const getUserProfileQuery = (userId: string) => {
  return useQuery<ApiResponse<UserData>, Error>({
    queryKey: ["users", userId],
    queryFn: () => getUserProfileRequest(userId)
  });
};

export const updateMyProfileMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({request}: {request: UpdateMeRequest}) => updateMyProfileRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<UserData>) => {
      if (data.result) {
        console.log("Update your profile: ", data.result);
        toast({
          title: "Profile updated successfully"
        });
        queryClient.invalidateQueries({queryKey: ["users", "me"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};
