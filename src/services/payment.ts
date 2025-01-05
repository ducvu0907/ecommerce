import { ApiResponse, CreatePaymentRequest, PaymentData } from "@/types/models";
import { _request } from "./request";
import { useMutation, useQuery } from "@tanstack/react-query";
import { toast } from "@/hooks/use-toast";

const getPaymentByOrder = async (orderId: string): Promise<ApiResponse<PaymentData>> => {
  return _request<ApiResponse<PaymentData>>({
    url: `/api/payments/order/${orderId}`,
    method: "GET"
  });
};

const createPayment = async (request: CreatePaymentRequest): Promise<ApiResponse<PaymentData>> => {
  return _request<ApiResponse<PaymentData>>({
    url: `/api/payments`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

export const getPaymentByOrderQuery = (orderId: string) => {
  return useQuery<ApiResponse<PaymentData>, Error>({
    queryKey: ["payment", orderId],
    queryFn: () => getPaymentByOrder(orderId),
  });
};

// FIXME: this might be incorrect
export const createPaymentMutation = () => {
  return useMutation({
    mutationFn: ({request}: {request: CreatePaymentRequest}) => createPayment(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<PaymentData>) => {
      if (data.result) {
        console.log("New payment: ", data.result);
        toast({
          title: "Create payment successfully",
        });

      } else {
        throw new Error(data.message);
      }
    },
  });
};