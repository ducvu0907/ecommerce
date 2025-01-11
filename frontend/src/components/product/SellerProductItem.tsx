import { ProductData } from "@/types/models";
import { Button } from "@/components/ui/button";
import { Trash, Eye } from "lucide-react";
import { deleteProductMutation } from "@/services/product";
import { useNavigate } from "react-router-dom";
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

interface SellerProductItemProps {
  product: ProductData;
}

const SellerProductItem: React.FC<SellerProductItemProps> = ({ product }) => {
  const { mutate: deleteProduct, isPending: deleteProductPending } = deleteProductMutation();
  const navigate = useNavigate();

  const handleDeleteProduct = () => {
    deleteProduct({productId: product.id});
  };

  return (
    <div className="flex items-center justify-between p-2 border-b">
      <div className="flex items-center space-x-4">
        <img
          src={"https://placehold.co/600x400"} // FIXME: placeholder image
          alt={product.title}
          className="w-10 h-10 object-cover rounded"
        />

        <div>
          <p className="font-medium">{product.title}</p>
          <p className="text-sm text-gray-500">${product.price} • {product.quantity} in stock</p>
        </div>
      </div>
      <div className="flex items-center space-x-2">
        <Button variant="ghost" size="sm" onClick={() => navigate(`/inventories/${product.id}`)}>
          <Eye className="h-4 w-4" />
        </Button>
        <AlertDialog>
          <AlertDialogTrigger asChild>
            <Button variant="ghost" size="sm">
              <Trash className="h-4 w-4" />
            </Button>
          </AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Are you sure?</AlertDialogTitle>
              <AlertDialogDescription>
                This action cannot be undone. This will permanently delete the product
                "{product.title}" and remove it from your inventory.
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel>Cancel</AlertDialogCancel>
              <AlertDialogAction
                onClick={handleDeleteProduct}
                disabled={deleteProductPending}
              >
                {deleteProductPending ? "Deleting..." : "Delete"}
              </AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
      </div>
    </div>
  );
};

export default SellerProductItem;