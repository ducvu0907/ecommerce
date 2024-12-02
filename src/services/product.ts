import { Product } from "@/types/models";
import { useQuery } from "@tanstack/react-query";

const getProductsRequest = async (): Promise<Product[]> => {
  const response = await fetch("api/products", {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch products");
  }

  return response.json();
};

const getProductsBySellerRequest = async (sellerId: string): Promise<Product[]> => {
  const response = await fetch(`api/products/seller/${sellerId}`, {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch products by seller");
  }

  return response.json();
};

const getProductRequest = async (productId: string): Promise<Product> => {
  const response = await fetch(`api/products/${productId}`, {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch product");
  }

  return response.json();
};

export const getProducts = () => {
  return useQuery<Product[], Error>({
    queryKey: ["products"],
    queryFn: getProductsRequest,
  });
};

export const getProductsBySeller = (sellerId: string) => {
  return useQuery<Product[], Error>({
    queryKey: ["products", "seller", sellerId],
    queryFn: () => getProductsBySellerRequest(sellerId)
  });
};

export const getProduct = (productId: string) => {
  return useQuery<Product, Error>({
    queryKey: ["products", productId],
    queryFn: () => getProductRequest(productId)
  });
};