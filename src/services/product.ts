import { CreateProductRequest, ProductData } from "@/types/models";
import { useMutation, useQuery } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getProductsRequest = async (): Promise<ApiResponse<ProductData[]>> => {
  return _request<ApiResponse<ProductData[]>>({
    url: "/api/products",
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const getProductsBySellerRequest = async (sellerId: string): Promise<ApiResponse<ProductData[]>> => {
  return _request<ApiResponse<ProductData[]>>({
    url: `/api/products/seller/${sellerId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const getProductRequest = async (productId: string): Promise<ApiResponse<ProductData>> => {
  return _request<ApiResponse<ProductData>>({
    url: `/api/products/${productId}`,
    method: "GET",
    headers: {"Content-Type":"application/json"}
  });
};

const createProductRequest = async (request: CreateProductRequest): Promise<ApiResponse<ProductData>> => {
  return _request<ApiResponse<ProductData>>({
    url: `/api/products/`,
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify(request)
  });
};

const searchProductsRequest = async (query: string): Promise<ApiResponse<ProductData[]>> => {
  return _request({
    url: `/api/products/search?query=${encodeURIComponent(query)}`,
    method: "GET",
    headers: {"Content-Type":"application/json"},
  });
};

export const getProducts = () => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["products"],
    queryFn: getProductsRequest,
  });
};

export const getProductsBySeller = (sellerId: string) => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["products", "seller", sellerId],
    queryFn: () => getProductsBySellerRequest(sellerId)
  });
};

export const getProduct = (productId: string) => {
  return useQuery<ApiResponse<ProductData>, Error>({
    queryKey: ["products", productId],
    queryFn: () => getProductRequest(productId)
  });
};

export const createProduct = () => {
  return useMutation({
    mutationFn: (request: CreateProductRequest) => createProductRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<ProductData>) => {
      if (data.result) {
        console.log("New product: ", data.result);
        toast({
          title: "Product created successfully"
        });
      } else {
        throw new Error(data.message);
      }
    },
  });
};

export const searchProducts = (query: string) => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["search", query],
    queryFn: () => searchProductsRequest(query)
  });
};