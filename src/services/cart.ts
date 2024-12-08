import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";
import { AddItemRequest, ApiResponse, AuthRequest, CartData, UpdateItemRequest } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getMyCartRequest = async (request: AuthRequest): Promise<ApiResponse<CartData>> => {
  return _request<ApiResponse<CartData>>({
    url: "/api/carts/cart",
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

const addItemRequest = async (request: AddItemRequest): Promise<ApiResponse<CartData>> => {
  return _request<ApiResponse<CartData>>({
    url: `/api/carts/items`,
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

const updateItemRequest = async (itemId: string, request: UpdateItemRequest): Promise<ApiResponse<CartData>> => {
  return _request<ApiResponse<CartData>>({
    url: `/api/carts/items/${itemId}`,
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

const deleteItemRequest = async (itemId: string, request: AuthRequest): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/carts/items/${itemId}`,
    method: "DELETE",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(request)
  });
};

export const getMyCart = (token: string) => {
  return useQuery<ApiResponse<CartData>, Error>({
    queryKey: ["cart"],
    queryFn: () => getMyCartRequest({token}),
    enabled: !!token
  });
}

export const addItem = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({token, productId, quantity}: {token: string, productId: string, quantity: number}) => addItemRequest({token, productId, quantity}),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<CartData>) => {
      if (data?.result) {
        toast({
          title: "Item added"
        });
        console.log("Add item to cart: ", data);
        queryClient.invalidateQueries({queryKey: ["cart"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const updateItem = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ itemId, token, quantity }: { itemId: string, token: string, quantity: number }) => updateItemRequest(itemId, { token, quantity }),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      })
    },
    onSuccess: (data: ApiResponse<CartData>) => {
      if (data?.result) {
        console.log("Update item in cart: ", data);
        toast({
          title: "Item updated successfully"
        });
        queryClient.invalidateQueries({queryKey: ["cart"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const deleteItem = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({ itemId, token }: { itemId: string, token: string }) => deleteItemRequest(itemId, { token }),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data?.result) {
        toast({
          title: data.result
        })

        queryClient.invalidateQueries({queryKey: ["cart"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};