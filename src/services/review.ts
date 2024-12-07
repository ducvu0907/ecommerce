import { ApiResponse, CreateReviewRequest, ReviewData } from "@/types/models";
import { _request } from "./request";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "@/hooks/use-toast";

const getReviewsByProductRequest = async (productId: string): Promise<ApiResponse<ReviewData[]>> => {
  return _request<ApiResponse<ReviewData[]>>({
    url: `/api/reviews/product/${productId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const createReviewRequest = async (request: CreateReviewRequest): Promise<ApiResponse<ReviewData>> => {
  return _request<ApiResponse<ReviewData>>({
    url: "/api/reviews",
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

export const getReviewsByProduct = (productId: string) => {
  return useQuery<ApiResponse<ReviewData[]>, Error>({
    queryKey: ["reviews", "product", productId],
    queryFn: () => getReviewsByProductRequest(productId)
  });
};

export const createReview = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({token, productId, rating, content}: {token: string, productId: string, rating: number, content: string}) => createReviewRequest({token, productId, rating, content}),
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
