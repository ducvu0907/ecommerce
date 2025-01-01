import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";
import { AddItemRequest, ApiResponse, CartData, UpdateItemRequest } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getMyCartRequest = async (): Promise<ApiResponse<CartData>> => {
  return _request<ApiResponse<CartData>>({
    url: "/api/carts/me",
    method: "GET",
  });
};

const addItemRequest = async (request: AddItemRequest): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/carts/me/items`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const updateItemRequest = async (itemId: string, request: UpdateItemRequest): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/carts/me/items/${itemId}`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const deleteItemRequest = async (itemId: string): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/carts/me/items/${itemId}`,
    method: "DELETE",
  });
};

const emptyCartRequest = async (): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/carts/me/items`,
    method: "DELETE",
  });
};

export const getMyCartQuery = () => {
  return useQuery<ApiResponse<CartData>, Error>({
    queryKey: ["cart"],
    queryFn: getMyCartRequest,
  });
}

export const addItemMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({request}: {request: AddItemRequest}) => addItemRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
        toast({
          title: data.result
        });
        console.log("Add item to cart: ", data);
        queryClient.invalidateQueries({queryKey: ["cart"]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const updateItemMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({itemId, request}: {itemId: string, request: UpdateItemRequest}) => updateItemRequest(itemId, request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      })
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
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

export const deleteItemMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({itemId}: {itemId: string}) => deleteItemRequest(itemId),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
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

export const emptyCartMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: emptyCartRequest,
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
        toast({
          title: data.result
        });
        queryClient.invalidateQueries({queryKey: ["cart"]});

      } else {
        throw new Error(data.message);
      }
    },
  });
};