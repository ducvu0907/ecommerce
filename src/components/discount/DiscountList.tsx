import { Table, TableBody, TableHead, TableHeader, TableRow } from "@/components/ui/table";
import { getDiscounts } from "@/services/discount";
import { DiscountData } from "@/types/models";
import DiscountItem from "./DiscountItem";
import { AlertTriangle, Loader2, X } from "lucide-react";
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "@/components/ui/dialog";
import { DialogDescription } from "@radix-ui/react-dialog";
import { isDiscountActive } from "@/helpers";
import { toast } from "@/hooks/use-toast";

interface DiscountListModalProps {
  isOpen: boolean;
  onClose: () => void;
  selectedDiscount: DiscountData | null;
  setSelectedDiscount: (discount: DiscountData | null) => void;
};

const DiscountListModal: React.FC<DiscountListModalProps> = ({ isOpen, onClose, selectedDiscount, setSelectedDiscount }) => {
  const { data, isLoading, isError } = getDiscounts();
  const discounts = data?.result;

  const handleSelectDiscount = (discount: DiscountData) => {
    if (isDiscountActive(discount)) {
      setSelectedDiscount(discount);
    } else {
      toast({
        title: "Discount is inactive",
        variant: "destructive"
      });
    }
  };

  if (isLoading) {
    return (
      <div className="flex justify-center items-center h-40">
        <Loader2 className="h-8 w-8 animate-spin text-blue-500" />
      </div>
    );
  }

  if (isError) {
    return (
      <div className="flex justify-center items-center h-40 text-destructive">
        <AlertTriangle className="h-8 w-8 mr-2" />
        <span>Error loading discounts</span>
      </div>
    );
  }

  if (!discounts || discounts.length === 0) {
    return <div className="text-center">No discounts available</div>
  }

  return (
    <Dialog open={isOpen} onOpenChange={onClose}>
      <DialogContent className="max-w-4xl">
        <DialogHeader>
          <DialogTitle>Available Discounts</DialogTitle>
          <DialogDescription className="text-gray-500">Click to choose a discount</DialogDescription>
        </DialogHeader>
        <div>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Description</TableHead>
                <TableHead>Discount</TableHead>
                <TableHead>Start Date</TableHead>
                <TableHead>End Date</TableHead>
                <TableHead>Status</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {discounts?.map((discount, idx) => (
                <DiscountItem
                  isSelected={!selectedDiscount ? false : selectedDiscount.id === discount.id}
                  key={idx}
                  discount={discount}
                  onSelect={() => handleSelectDiscount(discount)}
                />
              ))}
            </TableBody>
          </Table>
        </div>
      </DialogContent>
    </Dialog>
  );
};

export default DiscountListModal;