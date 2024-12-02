import { Category } from "@/types/models";
import { useQuery } from "@tanstack/react-query";

const getCategoriesRequest = async (): Promise<Category[]> => {
  const response = await fetch("api/categories", {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch all categories");
  }

  return response.json();
};

const getCategoryRequest = async (categoryId: string): Promise<Category> => {
  const response = await fetch(`api/categories/${categoryId}`, {
    method: "GET",
    headers: {"Content-Type": "application/json"},
  });

  if (!response.ok) {
    throw new Error("Network request failed: unable to fetch category");
  }

  return response.json();
};


export const getCategories = () => {
  return useQuery<Category[], Error>({
    queryKey: ["categories"],
    queryFn: getCategoriesRequest
  });
};

export const getCategory = (categoryId: string) => {
  return useQuery<Category, Error>({
    queryKey: ["categories", categoryId],
    queryFn: () => getCategoryRequest(categoryId)
  });
};