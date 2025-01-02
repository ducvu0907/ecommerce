import { useState } from "react";
import { createInventoryMutation } from "@/services/inventory";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { CreateInventoryRequest } from "@/types/models";

interface CreateInventoryDialogProps {
  isOpen: boolean;
  onClose: () => void;
  productId: string;
}

const CreateInventoryModal: React.FC<CreateInventoryDialogProps> = ({
  isOpen,
  onClose,
  productId,
}) => {
  const { mutate: createInventory, isPending } = createInventoryMutation();
  const [location, setLocation] = useState("");
  const [stock, setStock] = useState<number>(0);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    const inventoryData: CreateInventoryRequest = {
      productId,
      location,
      stock,
    };

    console.log(inventoryData);
    createInventory({ request: inventoryData });
    onClose();
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Create Inventory</DialogTitle>
        </DialogHeader>
        <form onSubmit={handleSubmit}>
          <div className="grid gap-4 py-4">
            <div className="grid gap-2">
              <Label htmlFor="location">Location</Label>
              <Input
                id="location"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                placeholder="Enter location"
                required
              />
            </div>
            <div className="grid gap-2">
              <Label htmlFor="stock">Stock</Label>
              <Input
                id="stock"
                type="number"
                value={stock}
                onChange={(e) => setStock(Number(e.target.value))}
                placeholder="Enter stock"
                required
              />
            </div>
          </div>
          <DialogFooter>
            <Button type="button" variant="outline" onClick={onClose}>
              Cancel
            </Button>
            <Button type="submit" disabled={isPending}>
              {isPending ? "Creating..." : "Create"}
            </Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default CreateInventoryModal;