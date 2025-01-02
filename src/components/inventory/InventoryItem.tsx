import { InventoryData } from "@/types/models";
import { Button } from "@/components/ui/button";
import { Trash, Pencil } from "lucide-react";
import { deleteInventoryMutation } from "@/services/inventory";
import { formatDate } from "@/helpers";
import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { useState } from "react";
import UpdateInventoryModal from "./UpdateInventoryModal";

interface InventoryItemProps {
  inventory: InventoryData;
}

const InventoryItem: React.FC<InventoryItemProps> = ({ inventory }) => {
  const [showUpdateModal, setShowUpdateModal] = useState<boolean>(false);
  const { mutate: deleteInventory, isPending } = deleteInventoryMutation();

  const handleDeleteInventory = () => {
    deleteInventory({inventoryId: inventory.id});
  };

  return (
    <div className="flex items-center justify-between p-2 border-b">

      <UpdateInventoryModal
        isOpen={showUpdateModal}
        onClose={() => setShowUpdateModal(false)}
        inventoryData={inventory}
      />

      <div className="flex items-center space-x-4">
        <div>
          <p className="font-medium">{inventory.location}</p>
          <p className="text-sm text-gray-500">
            Stock: {inventory.stock} • Last updated: {formatDate(inventory.updatedAt)}
          </p>
        </div>
      </div>
      <div className="flex items-center space-x-2">
        <Button variant="ghost" size="sm" onClick={() => setShowUpdateModal(true)}>
          <Pencil className="h-4 w-4" />
        </Button>
        <AlertDialog>
          <AlertDialogTrigger asChild>
            <Button variant="ghost" size="sm" disabled={isPending}>
              <Trash className="h-4 w-4" />
            </Button>
          </AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Are you sure?</AlertDialogTitle>
              <AlertDialogDescription>
                This action cannot be undone. This will permanently delete the inventory item.
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel>Cancel</AlertDialogCancel>
              <AlertDialogAction onClick={handleDeleteInventory}>
                {isPending ? "Deleting..." : "Delete"}
              </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      </div>
    </div>
  );
};

export default InventoryItem;