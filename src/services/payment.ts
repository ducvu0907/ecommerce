import { ApiResponse, CreatePaymentRequest, PaymentData } from "@/types/models";
import { _request } from "./request";
import { useMutation, useQuery } from "@tanstack/react-query";
import { toast } from "@/hooks/use-toast";

const getPaymentByOrderRequest = async (orderId: string): Promise<ApiResponse<PaymentData>> => {
  return _request<ApiResponse<PaymentData>>({
    url: `/api/payments/order/${orderId}`,
    method: "GET"
  });
};

export const getPaymentByOrderQuery = (orderId: string) => {
  return useQuery<ApiResponse<PaymentData>, Error>({
    queryKey: ["payments", orderId],
    queryFn: () => getPaymentByOrderRequest(orderId),
  });
};

const createPaymentRequest = async (request: CreatePaymentRequest): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/payments/create-payment`,
    method: "POST",
    body: JSON.stringify(request),
  });
};

export const createPaymentMutation = () => {
  return useMutation({
    mutationFn: ({request}: {request: CreatePaymentRequest}) => createPaymentRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
        window.location.href = data.result;
      } else {
        throw new Error(data.message);
      }
    },
  });
};