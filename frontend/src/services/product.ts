import { CreateProductRequest, ProductData, UpdateProductRequest } from "@/types/models";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { _request } from "./request";
import { ApiResponse } from "@/types/models";
import { toast } from "@/hooks/use-toast";

const getProductsRequest = async (): Promise<ApiResponse<ProductData[]>> => {
  return _request<ApiResponse<ProductData[]>>({
    url: "/api/products",
    method: "GET",
  });
};

const getProductsBySellerRequest = async (sellerId: string): Promise<ApiResponse<ProductData[]>> => {
  return _request<ApiResponse<ProductData[]>>({
    url: `/api/products/seller/${sellerId}`,
    method: "GET",
  });
};

const getProductRequest = async (productId: string): Promise<ApiResponse<ProductData>> => {
  return _request<ApiResponse<ProductData>>({
    url: `/api/products/${productId}`,
    method: "GET",
  });
};

const createProductRequest = async (request: CreateProductRequest): Promise<ApiResponse<ProductData>> => {
  return _request<ApiResponse<ProductData>>({
    url: `/api/products`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const deleteProductRequest = async (productId: string): Promise<ApiResponse<string>> => {
  return _request<ApiResponse<string>>({
    url: `/api/products/${productId}`,
    method: "DELETE",
  });
};

const updateProductRequest = async (productId: string, request: UpdateProductRequest): Promise<ApiResponse<ProductData>> => {
  return _request<ApiResponse<ProductData>>({
    url: `/api/products/${productId}`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const searchProductsRequest = async (query: string): Promise<ApiResponse<ProductData[]>> => {
  return _request({
    url: `/api/products/search?query=${encodeURIComponent(query)}`,
    method: "GET",
  });
};

export const getProductsQuery = () => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["products"],
    queryFn: getProductsRequest,
  });
};

export const getProductsBySellerQuery = (sellerId: string) => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["products", "seller"],
    queryFn: () => getProductsBySellerRequest(sellerId)
  });
};

export const getProductQuery = (productId: string) => {
  return useQuery<ApiResponse<ProductData>, Error>({
    queryKey: ["products", productId],
    queryFn: () => getProductRequest(productId)
  });
};

export const createProductMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({request}: {request: CreateProductRequest}) => createProductRequest(request),
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

        queryClient.invalidateQueries({queryKey: ["products", "seller"]})

      } else {
        throw new Error(data.message);
      }
    },
  });
};

export const updateProductMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({productId, request}: {productId: string, request: UpdateProductRequest}) => updateProductRequest(productId, request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<ProductData>) => {
      if (data.result) {
        console.log("Updated product: ", data.result);
        toast({
          title: "Product updated successfully"
        });

        queryClient.invalidateQueries({queryKey: ["products", "seller"]})
        queryClient.invalidateQueries({ queryKey: ["products", data.result.id] })

      } else {
        throw new Error(data.message);
      }
    },
  });
};

export const deleteProductMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({productId}: {productId: string}) => deleteProductRequest(productId),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<string>) => {
      if (data.result) {
        console.log("Delete product", data.result);
        toast({
          title: data.result
        });

        queryClient.invalidateQueries({queryKey: ["products", "seller"]})

      } else {
        throw new Error(data.message);
      }
    },
  });
};

export const searchProductsQuery = (query: string) => {
  return useQuery<ApiResponse<ProductData[]>, Error>({
    queryKey: ["search", query],
    queryFn: () => searchProductsRequest(query)
  });
};