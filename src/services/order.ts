import { toast } from "@/hooks/use-toast";
import { ApiResponse, CreateOrderRequest, OrderData } from "@/types/models";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";

const getMyOrdersRequest = async (): Promise<ApiResponse<OrderData[]>> => {
  return _request({
    url: "/api/orders",
    method: "GET",
  });
};

const getOrderRequest = async (orderId: string): Promise<ApiResponse<OrderData>> => {
  return _request({
    url: `/api/orders/${orderId}`,
    method: "GET",
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

const cancelOrderRequest = async (orderId: string): Promise<ApiResponse<string>> => {
  return _request({
    url: `/api/orders/${orderId}`,
    method: "DELETE",
  });
};


export const getMyOrdersQuery = () => {
  return useQuery<ApiResponse<OrderData[]>, Error>({
    queryKey: ["orders"],
    queryFn: getMyOrdersRequest
  });
};

export const getOrderQuery = (orderId: string) => {
  return useQuery<ApiResponse<OrderData>, Error>({
    queryKey: ["orders", orderId],
    queryFn: () => getOrderRequest(orderId)
  });
};

export const createOrderMutation = () => {
  return useMutation({
    mutationFn: ({ request }: { request: CreateOrderRequest }) => createOrderRequest(request),
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

export const cancelOrderMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({orderId}: {orderId: string}) => cancelOrderRequest(orderId),
    onError: (error: Error) => {
      toast({
        title: error.message
      });
    },
    onSuccess: (data: ApiResponse<string>, {orderId}: {orderId: string}) => {
      if (data?.result) {
        console.log("Cancel order", data);
        toast({
          title: data.result
        });

        queryClient.invalidateQueries({queryKey: ["orders", orderId]});

      } else {
        throw new Error(data.message);
      }
    }
  });
}; 