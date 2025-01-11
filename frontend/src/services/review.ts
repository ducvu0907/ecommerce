import { ApiResponse, CreateReviewRequest, ReviewData, UpdateReviewRequest } from "@/types/models";
import { _request } from "./request";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "@/hooks/use-toast";

const getReviewsByProductRequest = async (productId: string): Promise<ApiResponse<ReviewData[]>> => {
  return _request<ApiResponse<ReviewData[]>>({
    url: `/api/reviews/product/${productId}`,
    method: "GET",
  });
};

const createReviewRequest = async (request: CreateReviewRequest): Promise<ApiResponse<ReviewData>> => {
  return _request<ApiResponse<ReviewData>>({
    url: "/api/reviews",
    method: "POST",
    body: JSON.stringify(request)
  });
};

const updateReviewRequest = async (reviewId: string, request: UpdateReviewRequest): Promise<ApiResponse<ReviewData>> => {
  return _request<ApiResponse<ReviewData>>({
    url: `/api/reviews/${reviewId}`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const deleteReviewRequest = async (reviewId: string): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/reviews/${reviewId}`,
    method: "DELETE",
  });
};

export const getReviewsByProductQuery = (productId: string) => {
  return useQuery<ApiResponse<ReviewData[]>, Error>({
    queryKey: ["reviews", "product", productId],
    queryFn: () => getReviewsByProductRequest(productId)
  });
};

export const createReviewMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({request}: {request: CreateReviewRequest}) => createReviewRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<ReviewData>) => {
      if (data?.result) {
        console.log("Create new review: ", data);
        toast({
          title: "Review created successfully"
        });

        queryClient.invalidateQueries({queryKey: ["reviews", "product", data.result.productId]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const updateReviewMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({reviewId, request}: {reviewId: string, request: UpdateReviewRequest}) => updateReviewRequest(reviewId, request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<ReviewData>) => {
      if (data?.result) {
        console.log("Update review: ", data);
        toast({
          title: "Review updated successfully"
        });

        queryClient.invalidateQueries({queryKey: ["reviews", "product", data.result.productId]});

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const deleteReviewMutation = () => {
  return useMutation({
    mutationFn: ({reviewId}: {reviewId: string}) => deleteReviewRequest(reviewId),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data?.result) {
        console.log("Delete review: ", data);
        toast({
          title: data.result
        });

      } else {
        throw new Error(data.message);
      }
    }
  });
};