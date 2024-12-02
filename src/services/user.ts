import { User } from "@/types/models";
import { useQuery } from "@tanstack/react-query";

const getUsersRequest = async () => {
  const response = await fetch(`api/users/`, {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch all users");
  }

  return response.json();
};

const getUserRequest = async (userId: string) => {
  const response = await fetch(`api/users/${userId}`, {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch user");
  }

  return response.json();
};

export const getUser = (userId: string) => {
  return useQuery<User, Error>({
    queryKey: ["users", userId],
    queryFn: () => getUserRequest(userId)
  });
};

export const getUsers = () => {
  return useQuery<User[], Error>({
    queryKey: ["users"],
    queryFn: getUsersRequest
  });
  
};