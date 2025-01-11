import { ApiResponse, CreateInventoryRequest, InventoryData, UpdateInventoryRequest } from "@/types/models";
import { _request } from "./request";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import { toast } from "@/hooks/use-toast";

const getInventoriesByProductRequest = async (productId: string): Promise<ApiResponse<InventoryData[]>> => {
  return _request<ApiResponse<InventoryData[]>>({
    url: `/api/inventories/product/${productId}`,
    method: "GET",
  });
};

const createInventoryRequest = async (request: CreateInventoryRequest): Promise<ApiResponse<InventoryData>> => {
  return _request<ApiResponse<InventoryData>>({
    url: `/api/inventories`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const updateInventoryRequest = async (inventoryId: string, request: UpdateInventoryRequest): Promise<ApiResponse<InventoryData>> => {
  return _request<ApiResponse<InventoryData>>({
    url: `/api/inventories/${inventoryId}`,
    method: "POST",
    body: JSON.stringify(request)
  });
};

const deleteInventoryRequest = async (inventoryId: string): Promise<ApiResponse<InventoryData>> => {
  return _request<ApiResponse<InventoryData>>({
    url: `/api/inventories/${inventoryId}`,
    method: "DELETE"
  });
};

export const getInventoriesByProductQuery = (productId: string) => {
  return useQuery<ApiResponse<InventoryData[]>, Error>({
    queryKey: ["inventories", productId],
    queryFn: () => getInventoriesByProductRequest(productId)
  });
};

export const createInventoryMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({request}: {request: CreateInventoryRequest}) => createInventoryRequest(request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<InventoryData>) => {
      if (data.result) {
        console.log("New inventory: ", data.result);
        toast({
          title: "Inventory created successfully"
        });

        queryClient.invalidateQueries({queryKey: ["inventories", data.result.productId]})

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const updateInventoryMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({inventoryId, request}: {inventoryId: string, request: UpdateInventoryRequest}) => updateInventoryRequest(inventoryId, request),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<InventoryData>) => {
      if (data.result) {
        console.log("Update inventory: ", data.result);
        toast({
          title: "Inventory updated successfully"
        });

        queryClient.invalidateQueries({queryKey: ["inventories", data.result.productId]})

      } else {
        throw new Error(data.message);
      }
    }
  });
};

export const deleteInventoryMutation = () => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: ({inventoryId}: {inventoryId: string}) => deleteInventoryRequest(inventoryId),
    onError: (error: Error) => {
      toast({
        title: error.message,
        variant: "destructive"
      });
    },
    onSuccess: (data: ApiResponse<InventoryData>) => {
      if (data.result) {
        console.log("Update inventory: ", data.result);
        toast({
          title: "Inventory deleted successfully"
        });

        queryClient.invalidateQueries({queryKey: ["inventories", data.result.productId]})

      } else {
        throw new Error(data.message);
      }
    }
  });
};