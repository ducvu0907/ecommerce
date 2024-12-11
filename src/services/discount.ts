import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse, DiscountData } from "@/types/models";

const getDiscountsRequest = async (): Promise<ApiResponse<DiscountData[]>> => {
  return _request({
    url: "/api/discounts",
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

export const getDiscounts = () => {
  return useQuery<ApiResponse<DiscountData[]>, Error>({
    queryKey: ["discounts"],
    queryFn: getDiscountsRequest
  });
};
