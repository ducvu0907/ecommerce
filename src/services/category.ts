import { CategoryData } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";

const getCategoriesRequest = async (): Promise<ApiResponse<CategoryData[]>> => {
  return _request<ApiResponse<CategoryData[]>>({
    url: "/api/categories",
    method: "GET",
  });
};

export const getCategories = () => {
  return useQuery<ApiResponse<CategoryData[]>, Error>({
    queryKey: ["categories"],
    queryFn: getCategoriesRequest
  });
};
