import { useForm } from "react-hook-form";
import { createInventoryMutation } from "@/services/inventory";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import {
  Dialog,
  DialogContent,
  DialogHeader,
  DialogTitle,
  DialogFooter,
} from "@/components/ui/dialog";
import {
  Form,
  FormField,
  FormItem,
  FormLabel,
  FormControl,
  FormMessage,
} from "@/components/ui/form";
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
  const form = useForm<CreateInventoryRequest>({
    defaultValues: {
      productId,
      location: "",
      stock: 0,
    },
  });

  const handleSubmit = (data: CreateInventoryRequest) => {
    console.log(data);
    createInventory({ request: data });
    onClose();
  };

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent>
        <DialogHeader>
          <DialogTitle>Create Inventory</DialogTitle>
        </DialogHeader>
        <Form {...form}>
          <form onSubmit={form.handleSubmit(handleSubmit)} className="space-y-4">
            <FormField
              control={form.control}
              name="location"
              rules={{ required: "Location is required" }}
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
              control={form.control}
              name="stock"
              rules={{
                required: "Stock is required",
                min: { value: 0, message: "Stock must be at least 0" },
              }}
              render={({ field }) => (
                <FormItem>
                  <FormLabel>Stock</FormLabel>
                  <FormControl>
                    <Input
                      {...field}
                      type="number"
                      placeholder="Enter stock"
                      onChange={(e) => field.onChange(Number(e.target.value))}
                    />
                  </FormControl>
                  <FormMessage />
                </FormItem>
              )}
            />
            <DialogFooter>
              <Button type="button" variant="outline" onClick={onClose}>
                Cancel
              </Button>
              <Button type="submit" disabled={isPending}>
                {isPending ? "Creating..." : "Create"}
              </Button>
            </DialogFooter>
          </form>
        </Form>
      </DialogContent>
    </Dialog>
  );
};

export default CreateInventoryModal;