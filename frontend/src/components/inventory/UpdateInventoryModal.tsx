import { useForm } from "react-hook-form";
import { updateInventoryMutation } from "@/services/inventory";
import { InventoryData } from "@/types/models";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";
import { Input } from "@/components/ui/input";

interface UpdateInventoryForm {
  location: string;
  stock: number;
};

interface UpdateInventoryModalProps {
  isOpen: boolean;
  onClose: () => void;
  inventoryData: InventoryData;
};

const UpdateInventoryModal: React.FC<UpdateInventoryModalProps> = ({
  isOpen,
  onClose,
  inventoryData,
}) => {
  const { mutate: updateInventory, isPending } = updateInventoryMutation();

  const form = useForm<UpdateInventoryForm>({
    defaultValues: {
      location: inventoryData.location,
      stock: inventoryData.stock,
    },
  });

  const onSubmit = (data: UpdateInventoryForm) => {
    updateInventory({ inventoryId: inventoryData.id, request: data });
    onClose();
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="sm:max-w-md">
        <DialogHeader>
          <DialogTitle>Update Inventory</DialogTitle>
        </DialogHeader>
        <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
          <FormField
            name="location"
            control={form.control}
            render={({ field }) => (
              <FormItem>
                <FormLabel>Location</FormLabel>
                <FormControl>
                  <Input
                    {...field}
                    placeholder="Enter location"
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <FormField
            name="stock"
            control={form.control}
            render={({ field }) => (
              <FormItem>
                <FormLabel>Stock</FormLabel>
                <FormControl>
                  <Input
                    {...field}
                    type="number"
                    placeholder="Enter stock quantity"
                    min={0}
                  />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
          <DialogFooter className="sm:justify-end">
            <Button
              type="button"
              variant="outline"
              onClick={onClose}
              disabled={isPending}
            >
              Cancel
            </Button>
            <Button type="submit" disabled={isPending}>
              {isPending ? "Updating..." : "Update"}
            </Button>
          </DialogFooter>
        </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
};

export default UpdateInventoryModal;