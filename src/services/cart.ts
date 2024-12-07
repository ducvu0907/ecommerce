import { useMutation } from "@tanstack/react-query";
import { _request } from "./request";
import { AddItemRequest, ApiResponse, AuthRequest, CartData, UpdateItemRequest } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getMyCartRequest = async (request: AuthRequest): Promise<ApiResponse<CartData>> => {
  return _request({
    url: "/api/carts/cart",
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const addItemRequest = async (request: AddItemRequest): Promise<ApiResponse<CartData>> => {
  return _request({
    url: `/api/carts/items`,
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const updateItemRequest = async (itemId: string, request: UpdateItemRequest): Promise<ApiResponse<CartData>> => {
  return _request({
    url: `/api/carts/items/${itemId}`,
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const deleteItemRequest = async (itemId: string, request: AuthRequest): Promise<ApiResponse<string>> => {
  return _request({
    url: `/api/carts/items/${itemId}`,
    method: "DELETE",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

export const getMyCart = () => {
  return useMutation({
    mutationFn: (token: string) => {
      if (!token) {
        throw new Error("User not authenticated");
      }
      const request: AuthRequest = { token };
      return getMyCartRequest(request);
    },
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
  });
}

export const addItem = () => {

};

export const updateItem = () => {

};

export const deleteItem = () => {

};