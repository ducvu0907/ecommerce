import { Category } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";

const getCategoriesRequest = async (): Promise<ApiResponse<Category[]>> => {
  return _request<ApiResponse<Category[]>>({
    url: "api/categories",
    method: "GET",
    headers: {"Content-Type": "application/json"}
  });
};

const getCategoryRequest = async (categoryId: string): Promise<ApiResponse<Category>> => {
  return _request<ApiResponse<Category>>({
    url: `api/categories/${categoryId}`,
    method: "GET",
    headers: {"Content-Type": "application/json"}
  });
};

export const getCategories = () => {
  return useQuery<ApiResponse<Category[]>, Error>({
    queryKey: ["categories"],
    queryFn: getCategoriesRequest
  });
};

export const getCategory = (categoryId: string) => {
  return useQuery<ApiResponse<Category>, Error>({
    queryKey: ["categories", categoryId],
    queryFn: () => getCategoryRequest(categoryId)
  });
};