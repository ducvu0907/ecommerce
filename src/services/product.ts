import { Product } from "@/types/models";
import { useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";

const getProductsRequest = async (): Promise<ApiResponse<Product[]>> => {
  return _request<ApiResponse<Product[]>>({
    url: "api/products",
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const getProductsBySellerRequest = async (sellerId: string): Promise<ApiResponse<Product[]>> => {
  return _request<ApiResponse<Product[]>>({
    url: `api/products/seller/${sellerId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const getProductRequest = async (productId: string): Promise<ApiResponse<Product>> => {
  return _request<ApiResponse<Product>>({
    url: `api/products/${productId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

export const getProducts = () => {
  return useQuery<ApiResponse<Product[]>, Error>({
    queryKey: ["products"],
    queryFn: getProductsRequest,
  });
};

export const getProductsBySeller = (sellerId: string) => {
  return useQuery<ApiResponse<Product[]>, Error>({
    queryKey: ["products", "seller", sellerId],
    queryFn: () => getProductsBySellerRequest(sellerId)
  });
};

export const getProduct = (productId: string) => {
  return useQuery<ApiResponse<Product>, Error>({
    queryKey: ["products", productId],
    queryFn: () => getProductRequest(productId)
  });
};