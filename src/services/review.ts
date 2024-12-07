import { ApiResponse, CreateReviewRequest, ReviewData } from "@/types/models";
import { _request } from "./request";
import { useQuery } from "@tanstack/react-query";

const getReviewsByProductRequest = async (productId: string): Promise<ApiResponse<ReviewData[]>> => {
  return _request<ApiResponse<ReviewData[]>>({
    url: `/api/reviews/product/${productId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const createReviewRequest = async (request: CreateReviewRequest): Promise<ApiResponse<ReviewData>> => {

};

export const getReviewsByProduct = (productId: string) => {
  return useQuery<ApiResponse<ReviewData[]>, Error>({
    queryKey: ["reviews", "product", productId],
    queryFn: () => getReviewsByProductRequest(productId)
  });
};
