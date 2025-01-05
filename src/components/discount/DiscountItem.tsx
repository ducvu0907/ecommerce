import { TableRow, TableCell } from "@/components/ui/table";
import { Badge } from "@/components/ui/badge";
import { DiscountData, DiscountType } from "@/types/models";
import { formatDate } from "@/helpers";
import { isDiscountActive } from "@/helpers";
import { Check } from "lucide-react";

interface DiscountItemProps {
  discount: DiscountData;
  isSelected: boolean;
  onSelect: () => void;
};

const DiscountItem: React.FC<DiscountItemProps> = ({ discount, isSelected, onSelect }) => {
  const discountValue = discount.type === DiscountType.PERCENTAGE 
    ? `${discount.value}%` 
    : `$${discount.value.toFixed(2)}`;

  return (
    <TableRow 
      className={`cursor-pointer ${isSelected ? 'bg-blue-100' : ''}`} 
      onClick={onSelect}
    >
      <TableCell>
        {discount.description}
      </TableCell>
      <TableCell>{discountValue}</TableCell>
      <TableCell>{formatDate(discount.startDate)}</TableCell>
      <TableCell>{formatDate(discount.endDate)}</TableCell>
      <TableCell>
        <Badge variant={isDiscountActive(discount) ? "default" : "destructive"}>
          {isDiscountActive(discount) ? "Active" : "Inactive"}
        </Badge>
      </TableCell>
      {isSelected && (
        <TableCell>
          <Check className="text-green-500" />
        </TableCell>
      )}
    </TableRow>
  );
};

export default DiscountItem;
