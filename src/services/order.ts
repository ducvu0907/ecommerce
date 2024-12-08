import { toast } from "@/hooks/use-toast";
import { ApiResponse, AuthRequest, CreateOrderRequest, OrderData } from "@/types/models";
import { useMutation, useQuery } from "@tanstack/react-query";
import { _request } from "./request";

const getMyOrdersRequest = async (request: AuthRequest): Promise<ApiResponse<OrderData[]>> => {
  return _request({
    url: "/api/orders",
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const getOrderRequest = async (orderId: string, request: AuthRequest): Promise<ApiResponse<OrderData>> => {
  return _request({
    url: `/api/orders/${orderId}`,
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const createOrderRequest = async (request: CreateOrderRequest): Promise<ApiResponse<OrderData>> => {
  return _request({
    url: "/api/orders",
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

export const getMyOrders = (token: string) => {
  return useQuery<ApiResponse<OrderData[]>, Error>({
    queryKey: ["orders"],
    queryFn: () => getMyOrdersRequest({token})
  });
};

export const getOrder = (orderId: string, token: string) => {
  return useQuery<ApiResponse<OrderData>, Error>({
    queryKey: ["orders", orderId],
    queryFn: () => getOrderRequest(orderId, {token})
  });
};

export const createOrder = () => {
  return useMutation({
    mutationFn: createOrderRequest,
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
    onSuccess: (data: ApiResponse<OrderData>) => {
      if (data?.result) {
        console.log("Create order", data);
        toast({
          title: "Create order successfully"
        });
      } else {
        throw new Error(data.message);
      }
    }
  });
}; 