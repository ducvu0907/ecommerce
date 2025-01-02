import { useState } from "react";
import { getInventoriesByProductQuery } from "@/services/inventory";
import { useParams } from "react-router-dom";
import InventoryItem from "@/components/inventory/InventoryItem";
import { Skeleton } from "@/components/ui/skeleton";
import { Alert, AlertDescription, AlertTitle } from "@/components/ui/alert";
import { Button } from "@/components/ui/button";
import { Plus } from "lucide-react";
import CreateInventoryModal from "@/components/inventory/CreateInventoryModal";
import SellerProductInfo from "@/components/product/SellerProductInfo";

const Inventory: React.FC = () => {
  const { productId } = useParams<{ productId: string }>();
  const { data, isLoading, isError } = getInventoriesByProductQuery(productId || "");
  const inventories = data?.result;
  const [isModalOpen, setIsModalOpen] = useState(false);

  if (isLoading) {
    return (
      <div className="space-y-4">
        {Array.from({ length: 3 }).map((_, index) => (
          <Skeleton key={index} className="h-16 w-full" />
        ))}
      </div>
    );
  }

  if (isError || !productId) {
    return (
      <Alert variant="destructive">
        <AlertTitle>Error</AlertTitle>
        <AlertDescription>
          Failed to load inventory data. Please try again later.
        </AlertDescription>
      </Alert>
    );
  }

  return (
    <div className="space-y-4">
      
      <div className="flex justify-center items-center">
        <SellerProductInfo productId={productId} />
      </div>

      <div className="flex justify-between items-center">
        <h2 className="text-xl font-semibold">Inventory</h2>
        <Button
          onClick={() => setIsModalOpen(true)}
          className="flex items-center gap-2"
        >
          <Plus className="h-4 w-4" />
          Add Inventory
        </Button>
      </div>

      {(!inventories || inventories.length === 0) ? (
        <Alert>
          <AlertTitle>No Inventory Found</AlertTitle>
          <AlertDescription>
            There are no inventory records for this product.
          </AlertDescription>
        </Alert>
      ) : (
        <div className="space-y-4">
          {inventories.map((inventory) => (
            <InventoryItem key={inventory.id} inventory={inventory} />
          ))}
        </div>
      )}

      <CreateInventoryModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        productId={productId || ""}
      />
    </div>
  );
};

export default Inventory;