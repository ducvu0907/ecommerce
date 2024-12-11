import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Badge } from "@/components/ui/badge";
import { DiscountData } from "@/types/models";
import { Calendar, DollarSign, Percent } from "lucide-react";
import { formatDate } from "@/helpers";

interface DiscountItemProps {
  discount: DiscountData;
}

const DiscountItem: React.FC<DiscountItemProps> = ({ discount }) => {
  const discountValue = discount.percent !== null 
    ? `${discount.percent}%` 
    : discount.amount !== null 
      ? `$${discount.amount.toFixed(2)}` 
      : 'N/A';

  return (
    <Card className="mb-4">
      <CardHeader className="pb-2">
        <CardTitle className="flex items-center justify-between">
          <span>{discount.description}</span>
          <Badge variant={discount.isActive ? "default" : "destructive"}>
            {discount.isActive ? "Active" : "Inactive"}
          </Badge>
        </CardTitle>
      </CardHeader>
      <CardContent>
        <div className="grid grid-cols-2 gap-4">
          <div className="flex items-center space-x-2">
            {discount.percent !== null ? (
              <Percent className="h-5 w-5 text-blue-500" />
            ) : (
              <DollarSign className="h-5 w-5 text-green-500" />
            )}
            <span className="font-semibold">{discountValue}</span>
          </div>
          <div className="flex items-center space-x-2">
            <Calendar className="h-5 w-5 text-gray-500" />
            <span>
              {formatDate(discount.startDate)} - {formatDate(discount.endDate)}
            </span>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default DiscountItem;